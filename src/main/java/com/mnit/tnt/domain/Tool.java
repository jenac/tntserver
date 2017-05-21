package com.mnit.tnt.domain;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
//import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.Date;

/**
 * Created by jing on 3/1/17.
 */

@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
//@Data
public class Tool {
    @GraphId
    private Long id;
    private String toolName;
    private String description;
    private String imageUrl;

}
