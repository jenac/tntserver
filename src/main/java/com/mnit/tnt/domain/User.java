package com.mnit.tnt.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import lombok.Data;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by lihe on 2/11/17.
 */
@NodeEntity
@JsonIdentityInfo(generator = JSOGGenerator.class)
@Data
public class User implements Serializable {

    @GraphId
    private Long id;
//    private String userName;
//    private String password;
    private String firstName;
    private String lastName;
//    private String email;
//    private String stormPathHref;
//    private Boolean valid;
//    private Date dateCreated;
//    private Date dateUpdated;

    public User() {

    }

}
