precision highp float;

uniform vec3                iResolution;
uniform sampler2D           iChannel0;

uniform mediump float        red[9];
uniform mediump float        green[9];
uniform mediump float        blue[9];

varying vec2 texCoord;
varying vec2 lTexCoord;
varying vec2 rTexCoord;

varying vec2 tTexCoord;
varying vec2 tlTexCoord;
varying vec2 trTexCoord;

varying vec2 bTexCoord;
varying vec2 blTexCoord;
varying vec2 brTexCoord;

void main()
{
	mediump vec3 resultColor = texture2D(iChannel0, bTexCoord).xyz * vec3(red[0],green[0],blue[0]);
	resultColor += texture2D(iChannel0, blTexCoord).xyz * vec3(red[1],green[1],blue[1]);
	resultColor += texture2D(iChannel0, brTexCoord).xyz * vec3(red[2],green[2],blue[2]);
	resultColor += texture2D(iChannel0, texCoord).xyz * vec3(red[3],green[3],blue[3]);
	resultColor += texture2D(iChannel0, lTexCoord).xyz * vec3(red[4],green[4],blue[4]);
	resultColor += texture2D(iChannel0, rTexCoord).xyz * vec3(red[5],green[5],blue[5]);
	resultColor += texture2D(iChannel0, tTexCoord).xyz * vec3(red[6],green[6],blue[6]);
	resultColor += texture2D(iChannel0, trTexCoord).xyz * vec3(red[7],green[7],blue[7]);
	resultColor += texture2D(iChannel0, tlTexCoord).xyz * vec3(red[8],green[8],blue[8]);

	gl_FragColor = vec4(resultColor,1.);
}