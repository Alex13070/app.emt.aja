package org.dam2.appEmt.login.modelPeticion;


import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse implements Serializable {

	private String token;
	
}
