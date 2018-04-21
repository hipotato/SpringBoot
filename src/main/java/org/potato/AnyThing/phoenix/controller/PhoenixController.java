package org.potato.AnyThing.phoenix.controller;

import org.potato.AnyThing.imageMap.bo.MarkInfo;
import org.potato.AnyThing.imageMap.bo.Point;
import org.potato.AnyThing.phoenix.dao.generator.model.Poiph;
import org.potato.AnyThing.phoenix.dto.req.GetPoiReq;
import org.potato.AnyThing.phoenix.dto.req.ImageMapReq;
import org.potato.AnyThing.phoenix.dto.req.ImageMarkMapReq;
import org.potato.AnyThing.phoenix.dto.resp.BaseResp;
import org.potato.AnyThing.phoenix.dto.resp.NormalPoiResp;
import org.potato.AnyThing.phoenix.service.ImageMapService;
import org.potato.AnyThing.phoenix.service.PhoenixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by potato on 2018/4/2.
 */
@RestController
@RequestMapping("phoenix")
public class PhoenixController {

    @Autowired
    private PhoenixService phoenixService;

    @Autowired
    private ImageMapService imageMapService;

    @RequestMapping(value = "/getPoi")
    BaseResp getPoi(@Validated GetPoiReq req){
        return phoenixService.getPoi(req);
    }
    @RequestMapping(value = "/addPoi")
    String addPoi(){

        phoenixService.addPoi();
        return  "done";
    }
    @RequestMapping(value = "/getPoiByTypeCode")
    public NormalPoiResp getPoiByOtherId(String typecode){
        return  phoenixService.getPoiByTypeCode(typecode);
    }

    /**
     *
     * @param req
     * @return
     * @TestUrl http://localhost:9090/phoenix/getImageMap?left=116.3487&right=116.4302&top=39.9493&bottom=39.8972&height=2439&width=2039
     */
    @RequestMapping(value = "/getImageMap")
    @ResponseBody
    public ResponseEntity<byte[]> getImageMap(@Validated ImageMapReq req) {
        byte[] avatar = imageMapService.getImageMap(req);

        // 返回图片流
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(avatar, headers, HttpStatus.OK);
    }
    /**
     *
     * @param req
     * @return
     * @TestUrl http://localhost:9090/phoenix/getImageMarkMap?left=116.3487&right=116.4302&top=39.9493&bottom=39.8972&height=2439&width=2039
     */
    @RequestMapping(value = "/getImageMarkMap")
    @ResponseBody
    public ResponseEntity<byte[]> getImageMap(@Validated @RequestBody ImageMarkMapReq req) {
        byte[] avatar = imageMapService.getImageMarkMap(req);

        // 返回图片流
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(avatar, headers, HttpStatus.OK);
    }
    @RequestMapping(value = "/test")
    @ResponseBody
    public ResponseEntity<byte[]> test() {

        List<MarkInfo> marks = new LinkedList<>();
        marks.add(new MarkInfo(new Point(39.908675,116.397426),"北京","7:10","E:\\足迹册\\testPic\\tiananmen.jpg"));
        marks.add(new MarkInfo(new Point(39.275232,116.745292),"北京","8:45","E:\\足迹册\\testPic\\niaochao.jpg"));
        marks.add( new MarkInfo(new Point(40.000101,116.274944),"北京","9:02","E:\\足迹册\\testPic\\yiheyuan.jpg"));
        marks.add( new MarkInfo(new Point(40.359745,116.020059),"北京","10:02","E:\\足迹册\\testPic\\yiheyuan.jpg"));
        marks.add( new MarkInfo(new Point(39.639836,115.589344),"北京","12:02","E:\\足迹册\\testPic\\yiheyuan.jpg"));

        ImageMarkMapReq req = new ImageMarkMapReq(2039,2439,marks);

        byte[] avatar = imageMapService.getImageMarkMap(req);

        // 返回图片流
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(avatar, headers, HttpStatus.OK);
    }


}
