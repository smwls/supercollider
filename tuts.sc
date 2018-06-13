SuperDirt.start

{Pan2.ar(SinOsc.ar(4400, 0,0.1),0.0)}.play

{Pan2.ar(SinOsc.ar(MouseX.kr(50, 200), 0,0.1),0.0)}.play

(

{

	var n = 11; 	//try changing me to 34, or 3, and then re-running...



	Resonz.ar(

		Mix.fill(n,{



			var freq=rrand(50,560.3);

			var numcps= rrand(2,20);



			Pan2.ar(Gendy1.ar(6.rand,6.rand,1.0.rand,1.0.rand,freq ,freq, 1.0.rand, 1.0.rand, numcps, SinOsc.kr(exprand(0.02,0.2), 0, numcps/2, numcps/2), 0.5/(n.sqrt)), 1.0.rand2)

		}),

		MouseX.kr(100,2000),

		MouseY.kr(0.01,1.0)

	);

}.play

)
{Pan2.ar(SinOsc.ar(440,0,0.1),0.0)}.play


(		//double click on the inside of any bracket to highlight the code contained within



)





(		//select this code within the outer parentheses

		//then press the evalute key command

{

SinOsc.ar(440,0,0.1)

+

Pulse.ar(443,0.6,0.05)

}.play



)



Post << [2,3,4,5] <<nl;

2.postln;

(

var freq;

rand

freq=rrand(50,1000);

{FSinOsc.ar(440, 0,0.3)}.play


{Pan2.ar(SinOsc.ar(MouseX.kr(freq,2*freq),0,0.1), 0,0.2)}.play;

)
(

{Pan2.ar(LFSaw.ar(440,0,0.1), 0.1,0.3)}.play

if(4==4, {



	if(3==3, {

		"correct!".postln

	});



});



)

{LPF.ar(WhiteNoise.ar(0.1),Saw.ar(100,0,1))}.scope

{LPF.ar(SinOsc.ar(440,0,0.1),50)}.scope
FreqScope.new




{Resonz.ar(LFNoise0.ar(400),1000,0.1)}.scope

{Resonz.ar(LFNoise0.ar(400),Line.kr(10000,1000,10),0.1)}.scope



(

{

var source, line, filter; 	//local variables to hold objects as we build the patch up



source=LFNoise0.ar(400);

line=Line.kr(10000,1000,1);

filter=LPF.ar(source,line); //the filtered output is the input source filtered by Resonz with a line control for the resonant frequency



filter // last thing is returned from function in curly brackets, i.e. this is the final sound we hear

}.scope;

)


{SinOsc.ar([400,4000],0,0.1)}.scope

({
	var input, freq, pos, mixed_input;

	freq = MouseX.kr(100,1000);
	pos = MouseY.kr(-1,1);
	input = SinOsc.ar([freq + (pos*freq), freq - (pos*(freq/2))],0,0.1);
	mixed_input = Mix(input);
	Pan2.ar(mixed_input,pos);
}.scope)

Post << (100/2) << nl


({
	var n = 10;
	var wave = Mix.fill(10, {|i|
		var mult = ((-1)**i)*(0.5/((i+1)));
	SinOsc.ar(440*(i+1))*mult
	});
	Pan2.ar(wave/n,0.0)
}.scope)

({
	var n = 10;
	var wave = Mix.fill(10,{|i|
		var hnum = 2*i+1;
		SinOsc.ar(440*hnum)/hnum
	})*0.25;
	Pan2.ar(wave,0.0)
}.scope)
(

var n = 10;



{Mix(SinOsc.ar(250*(1..n),0,1/n))}.scope;



)

(1,..10)
({
	var n = 4;
	var notes = Mix.fill(n,{|i|
		var rand = rrand(200*i,(i+1)*200);
		SinOsc.ar(rand,0,0.1);
	})*0.25;
	Pan2.ar(notes,0.0)
}.play)


({
	var n = 10;
	var notes = Mix(SinOsc.ar(100*(1,4..n),0,1/n));
	Pan2.ar(notes,0);
}.scope)


{Pan2.ar(SinOsc.ar(mul:MouseX.kr(0.1,1.0),add:MouseY.kr(0.9,-0.9)))}.scope

({
	var cutoff = SinOsc.ar(1)*1700+2000;
	LPF.ar(WhiteNoise.ar,freq:cutoff)
}.scope)


({
	var phaseOffset = MouseX.kr(-pi,pi);
	var output = Mix(SinOsc.ar([440,440], [0,phaseOffset],0.1));
	Pan2.ar(output);
}.scope)

{SinOsc.ar(SinOsc.ar(3,mul:40,add:440),0,0.1)}.scope

a = {arg freq=440, amp=0.1; SinOsc.ar(freq)*amp}.play

a.run(false)
a.set(\freq,40, \amp, 0.4)
a.run

a.free



({
	var carrier,modulator,carrfreq,modfreq;
	carrfreq=MouseX.kr(440,500,'exponential');
	modfreq=MouseY.kr(1,5000,'exponential');
	carrier=SinOsc.ar(carrfreq,0,0.5);
	modulator=SinOsc.ar(modfreq,0,0.5);
	carrier*modulator;
}.scope)

({
	var carrier,modulator,carrfreq,modfreq;
	carrfreq=MouseX.kr(440,5000,'exponential');
	modfreq=MouseY.kr(1,5000,'exponential');
	carrier=SinOsc.ar(carrfreq,0,0.5);
	modulator=SinOsc.ar(modfreq,0,0.25,0.25);
	carrier*modulator;
}.scope)

(
	var w, carrfreqslider, modfreqslider,moddepthslider,synth;

	w=Window("freqmod", Rect(100,400,400,300));
	w.view.decorator=FlowLayout(w.view.bounds);
	synth={arg carrfreq=440,modfreq=1,moddepth=0.01;
		SinOsc.ar(carrfreq+(moddepth*SinOsc.ar(modfreq)),0,0.25)
	}.scope;

	carrfreqslider= EZSlider(w, 300@50, "carrfreq", ControlSpec(20, 5000, 'exponential', 10, 440), {|ez|  synth.set(\carrfreq, ez.value)});

w.view.decorator.nextLine;



modfreqslider= EZSlider(w, 300@50, "modfreq", ControlSpec(1, 5000, 'exponential', 1, 1), {|ez|  synth.set(\modfreq, ez.value)});

w.view.decorator.nextLine;

moddepthslider= EZSlider(w, 300@50, "moddepth", ControlSpec(0.01, 5000, 'exponential', 0.01, 0.01), {|ez|  synth.set(\moddepth, ez.value)});



w.front;
)

(

{

var modfreq, modindex;



modfreq= MouseX.kr(1,440, 'exponential');

modindex=MouseY.kr(0.0,10.0);



SinOsc.ar(SinOsc.ar(modfreq,0,modfreq*modindex, 440),0,0.25)

}.scope

)


(

{

var carrfreq, modfreq, harmonicity, modindex;



//fc is frequency of carrier

//fm is frequency of modulator



carrfreq= 440; //MouseY.kr(330,550);

harmonicity= MouseX.kr(0,10).round(1);

modindex= MouseY.kr(0.0,10.0); //which is really modulation amplitude/modulation frequency, acts as brightness control as energy distribution changed over components



modfreq= carrfreq*harmonicity; //since harmonicity is an integer,



SinOsc.ar(carrfreq+(SinOsc.ar(modfreq)*modfreq*modindex), 0.0,0.1);



}.play

)

(

{

var modfreq, modindex, conversion;



modfreq= MouseX.kr(1,1000, 'exponential');

modindex=MouseY.kr(0.0,100.0);

conversion= 2pi/(s.sampleRate);



//Phasor is a UGen which will loop around a given interval, in this case 0 to 2pi, taking us around the waveform of the sinusoid; note that all the action is in the phase input

//SinOsc.ar(0, Phasor.ar(0,440*conversion,0,2pi)+( (modfreq*modindex)*conversion*SinOsc.ar(modfreq)), 0.25)



//simpler alternative

SinOsc.ar(440, ( (modfreq*modindex)*conversion*SinOsc.ar(modfreq)), 0.25);

}.scope

)

[Env]
{SinOscFB.ar(440,0.9,0.2,0)}.play


{Mix(SinOsc.ar([301,430,450,670,885,980,1107],0,Line.kr(0.2,0,10,doneAction:2)))}.scope

(
{
    var env = Env.perc(0.05,0.5,1.0,0);
    SinOsc.ar(470) * EnvGen.kr(env, doneAction: Done.freeSelf)
}.play
)

Env([0, 1, 0.5, 1, 0], [0.01, 0.5, 0.02, 0.5]).plot

Env([1,0,1],[1,1]).plot

Env.adsr(0.01, 0.5, 0.5, 0.1, 1.0, 0).plot

Env.linen(0.03,0.5,0.1).plot

{EnvGen.ar(Env([1,0,1,0],[0.01,0.01,0.01]))}.scope

{SinOsc.ar(440,0,0.1)*EnvGen.kr(Env([1,0],[1.0]))}.scope

Env([1000,20],[1.0]).plot

{Saw.ar(EnvGen.ar(Env([500,340],[1.0])),0.1)}.scope

{Pan2.ar(Mix(SinOsc.ar([25,50,50,100,150,150,170,230,270,270,340,700]*EnvGen.ar(Env([1.8,1.6,1.8,1.2,1,1.2,1.1,0.8],[1,0.5,1,0.5,1,0.5]*3,\hold, 6,1),0.1),0,0.1)),0,0.3)}.scope



Line.kr(1.8,0.8,25,doneAction:2)
1.53.round(0.01)
100,150,180,210,230,270,340
Env([1.8,1.6,1.8,1.2,0.8,1.2,0.8,0.7],[1,0.5,1,0.5,1,0.5]*3,\hold).plot

{Saw.ar(EnvGen.ar(Env([10000,20],[0.5])),EnvGen.ar(Env([0.1,0],[2.0])))}.scope


({

	Saw.ar(

		EnvGen.kr(Env([10000,20],[0.5])),  //frequency input

		EnvGen.kr(Env([0.1,0],[2.0]))      //amplitude input

	)

}.play

)


({

SinOsc.ar(

	SinOsc.ar(10,0,10,440),

	0.0,

	EnvGen.kr(Env([0.5,0.0],[1.0]), doneAction:2)   //doneAction:2 appears again, the deallocation operation

	)

}.scope

)

{Saw.ar(EnvGen.kr(Env([500,100],[1.0]),doneAction:2),0.1)}.scope

{Saw.ar(SinOsc.ar(1,0,10,440),XLine.kr(0.0001,1,1,doneAction:2))}.scope

{EnvGen.ar(Env([0,0.1,0],[0.1,0.9]),doneAction:2)*SinOsc.ar(330)}.play

a = {EnvGen.ar(Env.asr(0.1,0.1,1.0),doneAction:2)*SinOsc.ar(330)}.play

a.release(2)

a = {arg gate=1;EnvGen.ar(Env.asr(0.1,0.1,0.9),gate,doneAction:2)*SinOsc.ar(330)}.play

a.set(\gate,0)

e = Env([0.2,1.0,0.0],[0.1,3.0],0,1);
a= {arg gate=1; EnvGen.ar(e,gate,doneAction:2)*SinOsc.ar(550,0,0.1)}.play

a.set(\gate,0)

e = Env([0.0,1.0,-1.0,0.0,0.2,-0.2],[0.1,0.1,1.0,0.3,0.2],0,2,0);

e.plot

a = {arg gate=1;EnvGen.ar(e,gate,timeScale:MouseX.kr(0.5,1),doneAction:2)}.scope

SynthDef(\sine,{
	Out.ar(0,Pan2.ar(SinOsc.ar(Rand(40,880),0,0.1)),0,0.2)
}).add

a=Synth(\sine);
b=Synth(\sine);
c=Synth(\sine);
d=Synth(\sine);
a.free
b.free
c.free
d.free

Server.default
s.serverRunning

SynthDef(\sin2,{
	arg freq=440,amp=0.01;
	Out.ar(0,Pan2.ar(Mix(SinOsc.ar(freq*(1..100),0,amp)),0,0.2));
}).add;

a=Synth("sin2")

a.set(\freq,500, \amp,0.01)



SynthDef(\spooky1, {arg mult=1, decay=2;

	var output = Pan2.ar(Mix(SinOsc.ar([25,50,50,100,150,150,230,270,270,340,700]*mult*(SinOsc.ar(mult+0.3)*0.01+1),0,0.1*EnvGen.ar(Env([1,0],[decay]),1,1,doneAction:2)),0,0.3));
Out.ar(0,output)}).add

a=Synth(\spooky1)
b=Synth(\chr)
a.set(\mult,2)
b.set(\mult,1.1)

SynthDescLib.global.synthDescs[\spooky1].def.func.postcs

SynthDescLib.global.browse


(

SynthDef(\event,{ arg freq=440, amp=0.5, pan=0.0;

	var env;



	env = EnvGen.ar(  Env([0,1,1,0],[0.01, 0.1, 0.2]),  doneAction:2);



	Out.ar(0,  Pan2.ar(Blip.ar(freq) * env * amp, pan))

}).add;

)

Synth(\event,[\freq,110,\amp,0.2,\pan,0])

(
{
	Synth(\event);
	1.0.wait;
	Synth(\event,[\freq,220]
	);
	1.0.wait;
	Synth(\event);
	1.0.wait;
	Synth(\event,[\freq,220]
	);
	1.0.wait;
	Synth(\event);
	1.0.wait;
	Synth(\event,[\freq,220]
	);
	1.0.wait;
}.fork;
)

(
var tempoclock = TempoClock(1);
a = {
	inf.do {|i|
		var j = rrand(0.8,2.3);
		Synth(\spooky1,[\mult,j, \decay,1.8/j]);
		1.wait;
		// (1.8/j).wait;
	};
}.fork(tempoclock);
)
a.stop
a.fork
TempoClock.default.tempo = 2


(
var tempoclock = TempoClock(1.1);
{
	4.do{
		Synth(\event,[\freq,60.midicps]);
		0.25.wait;
	}
}.fork(tempoclock)
)