#pragma strict

var velocity : float = 5.0;
var moveDelay : float = 1.0;
var sustainTime : float = 3.0;

function Start () {
	yield WaitForSeconds(moveDelay);
	
	var player : GameObject = GameObject.FindWithTag("Player");
	
	if(player != null)
	{	
		var direction : Vector3 = (player.transform.position - transform.position).normalized;
		rigidbody.AddForce(direction * velocity, ForceMode.VelocityChange);
	}
	
	yield WaitForSeconds(sustainTime);
	
	Destroy(gameObject);
}

function Update () {

}