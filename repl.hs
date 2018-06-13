d2 $ every 8 (|=| speed "[0.301. 0.101]")
   $ every 4 (|=| speed "[0.3, 0.1]")
   $ every 5 (|*| speed "-1")
   $ every 7 (fast 0.4)
   $ every 4 (|=| hpf 1000)
   $ every' 4 2 (|=| lpf 200)
   $ degradeBy 0.1
   $ swingBy (1/2) 3
   $ sound "[[newnotes newnotes newnotes], [newnotes newnotes], newnotes, newnotes]"
   # stack [fastcat [nir 1, nir 1.1, nir 1.2], fastcat [nir 1.3, nir 1.4], nir 1.5, nir 1.6]
   # speed 0.20
   # pan sine
   # gain 0.7
   # hpf 200


t1 (wash (chop 2) 4) $ sound "[[newnotes newnotes] [newnotes newnotes]]"
d1 silence
d4 $ sound "[808bd*2 [~ ~ ~ 808bd] ~]" # gain "[3.3 3]" # lpf 200
d2 silence
:t (*2) <> (+3)
d7 $ every 4 (0.5 ~>) $ sound "[ades2:1 ades2:3 [ades2:7*2], [~ ~ bend ~], [[armora*3]*4]]" # gain 2.5 # pan sine # lpf (slow 2 ((+500).(*1000) <$> sine))
d3 silence
d4 $ every 2 (|=| gain 0) $ sound "[tok]" # n "[13 25]" # speed 1 # gain 1
d4 silence

d7 $ sound "newnotes*2" # n "[11 7 12 13, 2 10 9 8]" # speed 0.2 # lpf 1000
d7 silence
d5 $ ("0.05" ~>) $ every 8 (|=| begin "0.26") $ every 4 (|=| begin "0.5") $ loopAt 1 $ sound "bev" # speed 0.02 # cut 1 # begin "0.47"
d5 silence
d8 $ sound "sax" # speed 0.5 # cut 1
d8 silence
d6 $
   $ every 4 (|=| speed "0.3")

:t speed (fastcat ["[1,1]", (2*) <$> rand])

   :t speed 1
   :t every 4
   :i ParamPattern
hush
d1 $ jux rev $ sound "notes*7" # n "[1..8]" # speed 0.2 # lpf 300 # vowel "e"
d1 silence
d2 $ sound "notes*7" # stack [fastcat [nir 1, nir 1.1, nir 1.2], fastcat [nir 1.3, nir 1.4], nir 1.5, nir 1.6]
stack [fastcat [nir 1, nir 1.1, nir 1.2], fastcat [nir 1.3, nir 1.4], nir 1.5, nir 1.6]
d2 silence

d2 $ sound "SinOsc.ar(1,1,1)"
d3 silence
d3 $ sound "[[notes? notes notes], [notes? notes], notes, notes]" # stack [fastcat [nir 1, nir 1.1, nir 1.2], fastcat [nir 1.3, nir 1.4], nir 1.5, nir 1.6] # speed (0.3)
cps (0.5)
:t notes
:t irand

let nir x = n (slow x $ irand 20)

d2 $ sound "supers" # n "c5"
d2 silence

import Sound.Tidal.Chords
let ch z y chn = every z (|=| n ((\x -> x - y) $ chord chn))

d9 $ every' 16 6 (|=| legato 1) $ every' 16 7 (\x -> x) $ (cat (chordProgression)) # s "tutorial3" # legato 1.15 # gain 0.4 ----[chrd 5 "thirteen", chrd 10 "thirteen", chrd 15 "thirteen", chrd 3 "thirteen"] # lpf 500 # hpf 100
   # crush 5
:t [1]*3
let chordProgression = getZipList $ (\x -> chrd x) <$> ZipList ((\x -> x) <$> [-7, -7, -7, -7, -9, -9, -13, -13]) <*> ZipList (cycle ["thirteen"]) --, "m7flat9", "thirteen"]
:t getZipList chordProgression
d3 $ n (cat ["f3", "f3", "f3", "f3", "f2", "f2" ,"b2" ,"b2"]) # s "tutorial3" # gain 1.5 # pan sine
let y = getZipList $ (*2) <$> ZipList [1, 2] <*>  ZipList [3, 4]
let minus x y = y + x
let chrd num cho = n ((minus num) $ chord cho)
d9 silence
:t n $ chord "major"
:t map
d8 $ sound "tutorial3*6" # breakUp (n $ chord "major")
d8 silence
:t sound "notes" # n (chord "major")

d5 $ n (cat ["[f3 d4, a4]", "[d4 c4, g4]"]) # sound "tutorial3" # gain 0.4 # pan 1
d7 $ stack [n "[g4 e4]", n $ cat ["[c5 g4]", "[~ c5 ~]", "[a5 a5 a5 a5 a5 a5]"], n "[c5 ~ g4 a4]", n "[g5 a5 b4 c5 b5 ~ e5 f5 g4]"]
   # s "tutorial3" # gain 0.4 # lpf 2000 # pan sine
d7 silence

d1 silence
d2 $ sound "[808bd*3]" # gain "[1 0.8 0.8]" # lpf 2000
d4 $ n "[[~ 0 0 0]*3]]" # s "hh27" # pan sine # gain 2 # lpf 500
d2 silence
d4 silence
d8 $ n (cat ["[a5, c4]","[e5, g5]","[a5, c4]","[e5, g5]","[e5, c4]", "[e5, bb3, c4]","[e5, g5]","[e5, c4]"])
   # sound "tutorial3" # gain 0.4 # pan 0
hush
let nir x = n (slow x $ irand 20)

d2 $ every 8 (|=| speed "[0.301. 0.101]")
   $ every 4 (|=| speed "[0.3, 0.1]")
   $ every 5 (|*| speed "-1")
   $ every 7 (fast 0.4)
   $ every 4 (|=| hpf 1000)
   $ every' 4 2 (|=| lpf 200)
   $ degradeBy 0.1
   $ swingBy (1/2) 3
   $ s "[[tutorial3 tutorial3], [tutorial3*3] [tutorial3*4], tutorial3]"
   # stack [fastcat [nir 1, nir 1.5], fastcat [nir 2, nir 2.5, nir 3], fastcat [nir 3.5, nir 4, nir 4.5, nir 5], nir 5.5]
   # speed 0.20
   # pan sine
   # gain 0.7
   # hpf 200
