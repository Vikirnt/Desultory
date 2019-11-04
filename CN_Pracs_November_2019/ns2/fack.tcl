set ns [new Simulator]
set nf [open out.nam w]
$ns namtrace-all $nf

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam out.nam &
	exit 0
}

# logic

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]
set n9 [$ns node]
set n10 [$ns node]

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n0 $n2 1Mb 10ms DropTail
$ns duplex-link $n0 $n3 1Mb 10ms DropTail
$ns duplex-link $n2 $n1 1Mb 10ms DropTail
$ns duplex-link $n3 $n5 1Mb 10ms DropTail
$ns duplex-link $n3 $n7 1Mb 10ms DropTail
$ns duplex-link $n4 $n5 1Mb 10ms DropTail
$ns duplex-link $n8 $n4 1Mb 10ms DropTail
$ns duplex-link $n3 $n9 1Mb 10ms DropTail
$ns duplex-link $n5 $n6 1Mb 10ms DropTail
$ns duplex-link $n8 $n9 1Mb 10ms DropTail
$ns duplex-link $n3 $n4 1Mb 10ms DropTail
$ns duplex-link $n6 $n10 1Mb 10ms DropTail
$ns duplex-link $n10 $n5 1Mb 10ms DropTail
$ns duplex-link $n10 $n4 1Mb 10ms DropTail


set tcp [new Agent/TCP/Fack]
$ns attach-agent $n0 $tcp

set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ftp set type_ FTP

set sink [new Agent/TCPSink]
$ns attach-agent $n10 $sink

$ns connect $tcp $sink

$ns at 0.5 "$ftp start"
$ns at 4.5 "$ftp stop"

# ending

$ns at 5.0 "finish"
$ns run
