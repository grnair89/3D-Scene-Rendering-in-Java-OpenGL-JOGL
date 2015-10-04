#version 120

// Phong shading fragment shader

//receive material properties from lightingParams

uniform vec4 ambientColor,diffuseColor,specularColor,lightColor,ambientLightColor;
uniform float ambientReflectionCoefficient,diffReflecCo,specularReflecCo,specularExponent;


//receive vector data from phong vertex shader
varying vec3 lightNormal,halfNormalVector,eyeNormalVector,normal;

void main()
{
	vec3 normalVector = normalize(normal);
	
	//calculate specular component
	vec4 a = ambientLightColor * ambientReflectionCoefficient * ambientColor;
	vec4 d = lightColor*diffReflecCo*diffuseColor*dot(lightNormal,normalVector);
	vec4 s = lightColor * specularReflecCo * specularColor * (pow( max(dot(normalVector, halfNormalVector ),0.0),specularExponent));
    
    //Adding ambiance, diffusion and specular properties for finding final color.
    vec4 color = a+d+s;
    
    color.a = 1.0;
    
    gl_FragColor = color;
}
