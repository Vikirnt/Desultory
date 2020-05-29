set ns [new Simulator]
set nf [open ut.nam w]
$ns namtrace-all $nf

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam ut.nam &
	exit 1
}

# Nodes
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]

# Links
$ns duplex-link $n0 $n2 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail
$ns duplex-link $n2 $n3 1Mb 10ms DropTail

$ns duplex-link-op $n0 $n2 orient right-down
$ns duplex-link-op $n1 $n2 orient right-up
$ns duplex-link-op $n2 $n3 orient right

# Queue
$ns queue-limit $n2 $n3 10
$ns duplex-link-op $n2 $n3 queuePos 0.5

# UDP flow
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0

set null0 [new Agent/Null]
$ns attach-agent $n3 $null0

# UDP CBR application 
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0
$cbr0 set packetSize_ 500
$cbr0 set interval_ 0.05

# TCP flow
set tcp0 [new Agent/TCP]
$ns attach-agent $n1 $tcp0

set sink [new Agent/TCPSink]
$ns attach-agent $n3 $sink

# TCP FTP application
set ftp [new Application/FTP]
$ftp attach-agent $tcp0
$ftp set type_ FTP

# Connections

$ns connect $udp0 $null0
$ns connect $tcp0 $sink

# Color setting
$ns color 1 Blue
$ns color 2 Red

$udp0 set class_ 1
$tcp0 set class_ 2

# Timeline
$ns at 0.25 "$ftp start"
$ns at 0.5 "$cbr0 start"
$ns at 3.0 "$ftp stop"
$ns at 4.5 "$cbr0 stop"

# Run
$ns at 5.0 "finish"
$ns run
