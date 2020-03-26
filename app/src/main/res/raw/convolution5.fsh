precision highp float;

uniform vec3          iResolution;
uniform sampler2D     iChannel0;

uniform float         kernel[25];

varying vec2 texCoord00;
varying vec2 texCoord01;
varying vec2 texCoord02;
varying vec2 texCoord03;
varying vec2 texCoord04;

varying vec2 texCoord10;
varying vec2 texCoord11;
varying vec2 texCoord12;
varying vec2 texCoord13;
varying vec2 texCoord14;

varying vec2 texCoord20;
varying vec2 texCoord21;
varying vec2 texCoord22;
varying vec2 texCoord23;
varying vec2 texCoord24;

varying vec2 texCoord30;
varying vec2 texCoord31;
varying vec2 texCoord32;
varying vec2 texCoord33;
varying vec2 texCoord34;

varying vec2 texCoord40;
varying vec2 texCoord41;
varying vec2 texCoord42;
varying vec2 texCoord43;
varying vec2 texCoord44;

void main()
{
	mediump vec3 resultColor = texture2D(iChannel0, texCoord00).xyz * kernel[0];
	resultColor += texture2D(iChannel0, texCoord01).xyz * kernel[1];
	resultColor += texture2D(iChannel0, texCoord02).xyz * kernel[2];
	resultColor += texture2D(iChannel0, texCoord03).xyz * kernel[3];
	resultColor += texture2D(iChannel0, texCoord04).xyz * kernel[4];
	
	resultColor += texture2D(iChannel0, texCoord10).xyz * kernel[5];
	resultColor += texture2D(iChannel0, texCoord11).xyz * kernel[6];
	resultColor += texture2D(iChannel0, texCoord12).xyz * kernel[7];
	resultColor += texture2D(iChannel0, texCoord13).xyz * kernel[8];
	resultColor += texture2D(iChannel0, texCoord14).xyz * kernel[9];
	
	resultColor += texture2D(iChannel0, texCoord20).xyz * kernel[10];
	resultColor += texture2D(iChannel0, texCoord21).xyz * kernel[11];
	resultColor += texture2D(iChannel0, texCoord22).xyz * kernel[12];
	resultColor += texture2D(iChannel0, texCoord23).xyz * kernel[13];
	resultColor += texture2D(iChannel0, texCoord24).xyz * kernel[14];
	
	resultColor += texture2D(iChannel0, texCoord30).xyz * kernel[15];
	resultColor += texture2D(iChannel0, texCoord31).xyz * kernel[16];
	resultColor += texture2D(iChannel0, texCoord32).xyz * kernel[17];
	resultColor += texture2D(iChannel0, texCoord33).xyz * kernel[18];
	resultColor += texture2D(iChannel0, texCoord34).xyz * kernel[19];
	
	resultColor += texture2D(iChannel0, texCoord40).xyz * kernel[20];
	resultColor += texture2D(iChannel0, texCoord41).xyz * kernel[21];
	resultColor += texture2D(iChannel0, texCoord42).xyz * kernel[22];
	resultColor += texture2D(iChannel0, texCoord43).xyz * kernel[23];
	resultColor += texture2D(iChannel0, texCoord44).xyz * kernel[24];

	gl_FragColor = vec4(resultColor, 1.0);
}