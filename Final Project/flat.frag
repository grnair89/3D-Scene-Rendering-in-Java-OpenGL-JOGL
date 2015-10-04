varying vec4 color;

uniform vec4 shapeColor;

//uniform sampler2D texture;


void main()
{
	// To add the final color of the fragment
		gl_FragColor = color*shapeColor;
	
}