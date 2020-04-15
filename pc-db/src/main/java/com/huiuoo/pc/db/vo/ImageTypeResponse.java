package com.huiuoo.pc.db.vo;

import com.huiuoo.pc.db.dto.ImageDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @项目名称：picture-book-app-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/3
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageTypeResponse {

    private Long typeId;
    private String followType;
    private List<ImageVO> imageVOList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ImageVO{
        private Long imageId;
        private String url;
        private String description;
        private List<TagVO> tagVOList;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TagVO{
        private Long tagId;
        private String tag;
    }
}
