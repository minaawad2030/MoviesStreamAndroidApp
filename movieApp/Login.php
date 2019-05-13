<?php
$username=$_POST['username'];
$password=$_POST['password'];

$con=mysqli_connect("localhost","root","","movies");
$selectQuery="Select* from user where username=? and password=?";
$stmt= $con->prepare($selectQuery);
$stmt->bind_param('ss',$username,$password);
$stmt->execute();
$stmt->store_result();
$stmt->bind_result($id,$name,$user,$email,$pass);
$response=array();
$response['login']=false;
$response['name']="null";
if($stmt->num_rows != 1)
{
	
}else
{
    $stmt->fetch();
    $response['login']=true;
    $response['name']=$name;   
}
echo json_encode($response);
?>