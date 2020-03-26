precision highp float;

uniform vec3                iResolution;
uniform sampler2D           iChannel0;
uniform float				kernel;

varying vec2 texCoord;

void main()
{
	gl_FragColor = vec4(texture2D(iChannel0, texCoord).xyz * kernel,1.0);
}