attribute vec2 vPosition;
attribute vec2 vTexCoord;

uniform highp float texelWidth;
uniform highp float texelHeight;

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
	gl_Position = vec4 ( vPosition.x, vPosition.y, 0.0, 1.0 );

	vec2 widthStep = vec2(texelWidth, 0.0);
	vec2 heightStep = vec2(0.0, texelHeight);
	vec2 widthHeightStep = vec2(texelWidth, texelHeight);
	vec2 widthNegativeHeightStep = vec2(texelWidth, -texelHeight);

	texCoord = vTexCoord;
	lTexCoord = vTexCoord - widthStep;
	rTexCoord = vTexCoord + widthStep;

	tTexCoord = vTexCoord - heightStep;
	tlTexCoord = vTexCoord - widthHeightStep;
	trTexCoord = vTexCoord + widthNegativeHeightStep;

	bTexCoord = vTexCoord + heightStep;
	blTexCoord = vTexCoord - widthNegativeHeightStep;
	brTexCoord = vTexCoord + widthHeightStep;
}