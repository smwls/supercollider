SuperDirt.start
{
	var left;
	var right;
	var initials = [72,68,64];
	var initial = Select.kr(Stepper.kr(Impulse.kr(0.05,0.1), 0, 0,initials.size,1), initials);
	var roots =    [initial,initial-5,initial-7];//,initial-3,initial-3,initial+2,initial-9,initial-8];
	var thirds =   [4 ,4 ,4];// ,4 ,4 ,4 ,4 ,4 ];
	var sevenths = [11,11,11];//,11,11,11,11,11];
	var global_freq_distortion = SinOsc.ar(0.1, 0,3);
	var root = Select.kr( Stepper.kr( Impulse.kr(0.2,0.1), 0,0,roots.size,1), roots);
	var third_type = Select.kr(Stepper.kr( Impulse.kr(0.2, 0.1),0,0,thirds.size,1), thirds);
	var seventh_type = Select.kr(Stepper.kr( Impulse.kr(0.2, 0.1),0,0,sevenths.size,1), sevenths);
	var chord = global_freq_distortion + [root-12,root-5,root,root+2,root+third_type,root+7,root+seventh_type,].midicps;
	var chord_down_a_fifth = global_freq_distortion + [root-7-12,root-7-5,root-7,root-7+2,root-7+third_type,root-7+7,root-7+seventh_type,].midicps;
	var phaseOffset = 1.5;//Select.kr(Stepper.kr( Impulse.kr(1, 0.1), 0,0,4,0), ([0,0.5]));
	left = Mix.ar(
			[
			    SinOsc.ar((root-31).midicps,0.5, 0.04),
				SinOsc.ar(chord,     0,   0.01),
				SinOsc.ar(chord+0.5, 1,   0.01),
				SinOsc.ar(chord-0.5, 1,   0.01),
				SinOsc.ar(chord+0.2, 1.5, 0.01),
				SinOsc.ar(chord-0.2, 1.5, 0.01),
				SinOsc.ar(chord+0.1, 2.5, 0.01),
				SinOsc.ar(chord-0.1, 2.5, 0.01),
				SinOsc.ar(chord-7.midicps,     0,   0.01),
			    SinOsc.ar(chord_down_a_fifth+0.5, 1  +phaseOffset,   0.01),
			    SinOsc.ar(chord_down_a_fifth-0.5, 1  +phaseOffset,   0.01),
				SinOsc.ar(chord_down_a_fifth+0.2, 1.5+phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth-0.2, 1.5+phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth+0.1, 2.5+phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth-0.1, 2.5+phaseOffset, 0.01),
		    ]);
	right = Mix.ar(
			[
				SinOsc.ar((root-31).midicps + 2,0.5,0.04),
				SinOsc.ar(chord+2,   0,   0.01),
				SinOsc.ar(chord+2.5, 1,   0.01),
				SinOsc.ar(chord-2.5, 1,   0.01),
				SinOsc.ar(chord+2.2,-1.5, 0.01),
				SinOsc.ar(chord-2.2,-1.5, 0.01),
				SinOsc.ar(chord+2.1,-2.5, 0.01),
				SinOsc.ar(chord-2.1,-2.5, 0.01),
				SinOsc.ar(chord_down_a_fifth+2,   0,   0.01),
				SinOsc.ar(chord_down_a_fifth+2.5, 1-phaseOffset,   0.01),
				SinOsc.ar(chord_down_a_fifth-2.5, 1-phaseOffset,   0.01),
				SinOsc.ar(chord_down_a_fifth+2.2,-1.5-phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth-2.2,-1.5-phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth+2.1,-2.5-phaseOffset, 0.01),
				SinOsc.ar(chord_down_a_fifth-2.1,-2.5-phaseOffset, 0.01),
		    ]
		);
	Pan2.ar([left, right], 0,1)
}.scope