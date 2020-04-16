package com.huiuoo.pc.db.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @项目名称：picture-book-new
 * @类描述：
 * @创建人：lhf
 * @创建时间：2020/4/16
 * @version：V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GifGetResponse {

    private String typeName;
    private List<ModelVO> modelVOList;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ModelVO{
        private String modelName;
        private List<GifVO> gifVOList;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GifVO{
        private Long gifId;
        private String gifUrl;
        private String gifDesc;
    }
}
