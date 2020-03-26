precision highp float;

uniform vec3                iResolution;
uniform sampler2D           iChannel0;

uniform mediump mat3        convo;
uniform mediump float       offset;

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
	mediump vec4 bColor = texture2D(iChannel0, bTexCoord);
	mediump vec4 blColor = texture2D(iChannel0, blTexCoord);
	mediump vec4 brColor = texture2D(iChannel0, brTexCoord);
	mediump vec4 centerColor = texture2D(iChannel0, texCoord);
	mediump vec4 lColor = texture2D(iChannel0, lTexCoord);
	mediump vec4 rColor = texture2D(iChannel0, rTexCoord);
	mediump vec4 tColor = texture2D(iChannel0, tTexCoord);
	mediump vec4 trColor = texture2D(iChannel0, trTexCoord);
	mediump vec4 tlColor = texture2D(iChannel0, tlTexCoord);

	mediump vec4 resultColor = tlColor * convo[0][0] + tColor * convo[0][1] + trColor * convo[0][2];
	resultColor += lColor * convo[1][0] + centerColor * convo[1][1] + rColor * convo[1][2];
	resultColor += blColor * convo[2][0] + bColor * convo[2][1] + brColor * convo[2][2];

	gl_FragColor = vec4(resultColor.xyz + vec3(offset),1.);
}