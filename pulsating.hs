-- 120bpm: (1 cps = 4 bps = 240 bpm)
cps(0.4)


let five = ostinato 5
let four = ostinato 4
:t five 1
let five_four x = [five x, four x]

d1 $ n "[0 0 0 0 0, 7 7 7 7]" # sound "rhodey_sc"
d1 silence
hush

take 3 $ intercalate " " $ repeat $ show 2

:t stack [n "1 2 3", n "4 5 6"]
"1 2 3" :: Pattern Int