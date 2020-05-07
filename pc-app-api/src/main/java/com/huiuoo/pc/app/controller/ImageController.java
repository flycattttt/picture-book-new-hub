package com.huiuoo.pc.app.controller;


import com.huiuoo.pc.db.service.IImageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/3/17
 * @version：V1.0
 */
@RequestMapping("/image")
@RestController
public class ImageController {

    private final IImageService imageService;

    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * @Description: 获取图片目录
     * @param: type 背景：1 ； 元素：2 ； 模型：3
     * @return:
     */
 /*   @IgnoreJwtVerify
    @GetMapping("/materialType/list")
    public ImageIndexResponse getMaterialType(Integer type,Integer number) {

        return imageService.getMaterialTypeByType(type,number);
    }
*/
    /**
     * @Description: 分页获取分类下的图片
     * @param:
     * @return:
     */
    /*@IgnoreJwtVerify
    @GetMapping("/list")
    public List<ImageDO> getImageList(@Valid ImageGetRequest request) {
        return imageService.getImageList(request);
    }*/

    /**
     * @Description: 获取模型图片
     * @param: type：3
     * @return:
     */
   /* @IgnoreJwtVerify
    @GetMapping("/model/list")
    public List<ImageModelResponse> getImageModelList(@Valid ImageGetRequest request) {
        return imageService.getImageModelList(request);
    }
*/
}
