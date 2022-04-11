package org.dam2.appEmt.modeloUser;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
//@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
//@Entity
public class User implements Serializable{
    
    private String user;
    private String password;

}
