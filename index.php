Welcome!!!
<?php 

exec("gpio mode 0 out");
exec("gpio mode 2 out");
exec("gpio mode 8 out");

if (isset($_GET['btn1'])) {	// for Lock and Unlock
	 if($_GET['btn1'] == 1) {
system("cd /usr/lib/cgi-bin && sudo python welcome.py");
	exec("sudo python /home/pi/Desktop/Visitors/accept.py");
         
			} 
	

}
if(isset($_GET['btn2'])) {     // for alert to visitor
	if($_GET['btn2'] == 1) {
		system("cd /usr/lib/cgi-bin && sudo python script.py");
   }
}

?>
