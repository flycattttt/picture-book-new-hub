package com.huiuoo.pc.db.dao;

import com.huiuoo.pc.db.dataobject.FontDO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Description  
 * @Author  lhf
 * @Date 2020-05-20 
 */

public interface FontDao extends JpaRepository<FontDO,Long> {


}
