attribute vec2 vPosition;
attribute vec2 vTexCoord;

uniform highp float texelWidth;
uniform highp float texelHeight;

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
	gl_Position = vec4 ( vPosition.x, vPosition.y, 0.0, 1.0 );

	vec2 step10 = vec2(texelWidth, 0.0);
	vec2 step01 = vec2(0.0, texelHeight);
	vec2 step20 = vec2(texelWidth*2., 0.0);
	vec2 step02 = vec2(0.0, texelHeight*2.);
	vec2 step11 = vec2(texelWidth, texelHeight);
	vec2 step1n1 = vec2(texelWidth, -texelHeight);
	vec2 step22 = vec2(texelWidth*2., texelHeight*2.);
	vec2 step2n2 = vec2(texelWidth*2., -texelHeight*2.);
	vec2 step21 = vec2(texelWidth*2., texelHeight);
	vec2 step2n1 = vec2(texelWidth*2., -texelHeight);
	vec2 step12 = vec2(texelWidth, texelHeight*2.);
	vec2 step1n2 = vec2(texelWidth, -texelHeight*2.);
	
	texCoord00 = vTexCoord + step2n2;
	texCoord01 = vTexCoord + step1n2;
	texCoord02 = vTexCoord - step02;
	texCoord03 = vTexCoord - step12;
	texCoord04 = vTexCoord - step22;

	texCoord10 = vTexCoord - step21;
	texCoord11 = vTexCoord - step11;
	texCoord12 = vTexCoord - step01;
	texCoord13 = vTexCoord + step1n1;
	texCoord14 = vTexCoord + step2n1;

	texCoord20 = vTexCoord - step20;
	texCoord21 = vTexCoord - step10;
	texCoord22 = vTexCoord;
	texCoord23 = vTexCoord + step10;
	texCoord24 = vTexCoord + step20;
	
	texCoord30 = vTexCoord - step2n1;
	texCoord31 = vTexCoord - step1n1;
	texCoord32 = vTexCoord + step01;
	texCoord33 = vTexCoord + step11;
	texCoord34 = vTexCoord + step21;

	texCoord40 = vTexCoord - step2n2;
	texCoord41 = vTexCoord - step1n2;
	texCoord42 = vTexCoord + step02;
	texCoord43 = vTexCoord + step12;
	texCoord44 = vTexCoord + step22;
}