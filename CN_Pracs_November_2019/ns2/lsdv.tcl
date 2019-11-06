set ns [new Simulator]
set nf [open sw.nam w]
set namf [open out.tr w]

$ns namtrace-all $nf
$ns trace-all $namf

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam sw.nam &
	exit 0
}

#nodes
for {set i 0} {$i < 12} {incr i} {
  set n($i) [$ns node]
}

#links
for {set i 0} {$i < 8} {incr i} {
  $ns duplex-link $n($i) $n([expr $i + 1]) 1Mb 10ms DropTail
}

$ns duplex-link $n(1) $n(10) 1Mb 10ms DropTail
$ns duplex-link $n(0) $n(9) 1Mb 10ms DropTail
$ns duplex-link $n(9) $n(11) 1Mb 10ms DropTail
$ns duplex-link $n(10) $n(11) 1Mb 10ms DropTail
$ns duplex-link $n(11) $n(5) 1Mb 10ms DropTail

#UDP flow
set udp0 [new Agent/UDP]
$ns attach-agent $n(0) $udp0

set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0

set udp1 [new Agent/UDP]
$ns attach-agent $n(1) $udp1

set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null0 [new Agent/Null]
$ns attach-agent $n(5) $null0

#LS
$ns rtproto LS
$ns rtmodel-at 10.0 down $n(11) $n(5)
$ns rtmodel-at 15.0 down $n(7) $n(6)
$ns rtmodel-at 20.0 up $n(7) $n(6)
$ns rtmodel-at 30.0 up $n(11) $n(5)

#connections
$ns connect $udp0 $null0
$ns connect $udp1 $null0

#color
$ns color 1 Red
$ns color 2 Blue

$udp0 set fid_ 1
$udp1 set fid_ 2

#timeline
$ns at 0.1 "$cbr0 start"
$ns at 0.5 "$cbr1 start"
$ns at 4.0 "$cbr0 stop"
$ns at 4.5 "$cbr1 stop"
$ns at 5.0 "finish"

#run
$ns run
