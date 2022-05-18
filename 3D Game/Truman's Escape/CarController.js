#pragma strict

var lMax : float;
var rMax : float;
var direction : float;
var x : float;
var y : float;
var startPosition : Vector3;
var startRotation : Quaternion;

/*
function OnCollisionEnter(collisionInfo : Collision)
{

	Debug.Log("CarCollisionEnter");
	if(collisionInfo.gameObject.tag == "Player")
	{
		
		yield WaitForSeconds(3.0);
		transform.position = startPosition;
		transform.rotation = startRotation;
		
	}
}
*/

function Start () {
	
	x = transform.position.x;
	y = transform.position.y;
	startPosition = transform.position;
	startRotation = transform.rotation;

}

function Update () {
	
	if(lMax > transform.position.z || transform.position.z > rMax)
	{
		direction *= -1.0;
		transform.Rotate(Vector3.up * 180.0);
	}
	transform.position = new Vector3(x, y, transform.position.z + direction * Time.deltaTime);
	
}