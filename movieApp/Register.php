<?php
$name=$_POST['name'];
$username=$_POST['username'];
$password=$_POST['password'];
$email=$_POST['email'];

$con=mysqli_connect("localhost","root","","movies");
$query="insert into user (name,username,email,password) values (?,?,?,?)";
	$stmt=mysqli_prepare($con,$query);
	$stmt->bind_param('ssss',$name,$username,$email,$password);
	$stmt->execute();

    $respose=array();
    $respose['register']=true;
    echo json_encode($respose);
?>