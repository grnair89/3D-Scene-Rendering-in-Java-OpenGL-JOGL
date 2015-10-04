//
// lightingParams.java
//
// Simple class for setting up the viewing and projection transforms
// for the Shading Assignment.
//
// Students are to complete this class.
//

import javax.media.opengl.*;
import javax.media.opengl.fixedfunc.*; 


/**
 * 
 * @modified by Ganesh Rajasekharan
 *
 */
public class lightingParams
{

	// Add any global class variables you need here.

	//Material properties of the objects.
	float ambientColor[] = {1.0f,1.0f,1.0f,1.0f};
	float ambientReflectionCoefficient = 0.5f;

	float diffuseColor[] = {1.0f,1.0f,1.0f,1.0f};
	float diffReflecCo = 0.7f;

	float specularColor[] = { 1.0f,1.0f,1.0f,1.0f};
	float specularExponent = 10.0f;
	float specularReflecCo =  0.5f;


	//Properties of the light source
	float lightColor[] = {1.0f,1.0f,1.0f,1.0f};
	float lightPosition[] = {0.0f,5.0f,2.0f,1.0f};

	//Properties of the ambient light
	float ambientLightColor[] = {1.0f,1.0f,1.0f,1.0f};


	/**
	 * constructor
	 */
	public lightingParams()
	{

	}
	/**
	 * This functions sets up the lighting, material, and shading parameters
	 * for the Phong shader.
	 *
	 * You will need to write this function, and maintain all of the values
	 * needed to be sent to the vertex shader.
	 *
	 * @param program - The ID of an OpenGL (GLSL) shader program to which
	 * parameter values are to be sent
	 *
	 * @param gl2 - GL2 object on which all OpenGL calls are to be made
	 *
	 */
	public void setUpPhong (int program, GL2 gl2)
	{
		// Add your code here.

		//send ambient, diffused and specular components to shaders.
		int ambID = gl2.glGetUniformLocation(program,"ambientColor");
		gl2.glUniform4fv( ambID, 1, ambientColor, 0);

		int dID = gl2.glGetUniformLocation(program,"diffuseColor");
		gl2.glUniform4fv( dID, 1, diffuseColor, 0);

		int specC = gl2.glGetUniformLocation(program,"specularColor");
		gl2.glUniform4fv( specC, 1, specularColor, 0);


		//send ambient, diffused and specular reflection coefficients to shaders.
		int ambCoID = gl2.glGetUniformLocation(program,"ambientReflectionCoefficient");
		gl2.glUniform1f(ambCoID,ambientReflectionCoefficient);

		int diffCoID = gl2.glGetUniformLocation(program,"diffReflecCo");
		gl2.glUniform1f(diffCoID,diffReflecCo);

		int speCoID = gl2.glGetUniformLocation(program,"specularReflecCo");
		gl2.glUniform1f(speCoID,specularReflecCo);

		int speID = gl2.glGetUniformLocation(program,"specularExponent");
		gl2.glUniform1f(speID,specularExponent);


		//send light color and light position to shaders
		int lightPosID = gl2.glGetUniformLocation(program,"lightPosition");
		gl2.glUniform4fv( lightPosID, 1, lightPosition, 0);

		int lightColorID = gl2.glGetUniformLocation(program,"lightColor");
		gl2.glUniform4fv( lightColorID, 1, lightColor, 0);

		//send ambient light to the shaders.
		int amLightID = gl2.glGetUniformLocation(program,"ambientLightColor");
		gl2.glUniform4fv( amLightID, 1, ambientLightColor, 0);
	}
}
