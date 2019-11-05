set ns [new Simulator]
set nf [open sw.nam w]
$ns namtrace-all $nf

# for monitoring window size
set namf [open sw.tr w]
$ns trace-all $namf

proc finish {} {
	global ns nf
	$ns flush-trace
	close $nf
	exec nam sw.nam &
	exit 0
}

# Nodes
set n0 [$ns node]
set n1 [$ns node]

$ns at 0.0 "$n0 label Sender"
$ns at 0.0 "$n1 label Receiver"

# Linking
$ns duplex-link $n0 $n1 0.2Mb 200ms DropTail

$ns duplex-link-op $n0 $n1 orient right
$ns queue-limit $n0 $n1 10

# TCP
Agent/TCP set nam_tracevar_ true

set tcp [new Agent/TCP]
$tcp set packetSize_ 500
$tcp set windowInit_ 1
$tcp set maxcwnd_ 1 
$ns attach-agent $n0 $tcp

set sink [new Agent/TCPSink]
$ns attach-agent $n1 $sink

# Monitoring
$ns add-agent-trace $tcp tcp
$ns monitor-agent-trace $tcp
$tcp tracevar cwnd_

# Application
set ftp [new Application/FTP]
$ftp set type_ FTP
$ftp attach-agent $tcp

# Connection
$ns connect $tcp $sink

# Timeline
$ns at 0.1 "$ftp start"
$ns at 4.5 "$ftp stop"
$ns at 5.0 "finish"

# Run
$ns run
