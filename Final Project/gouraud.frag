#version 120

// Gouraud shading fragment shader

//get color from gouraud vertex shader
varying vec4 color;

void main()
{
    gl_FragColor = color;
}
