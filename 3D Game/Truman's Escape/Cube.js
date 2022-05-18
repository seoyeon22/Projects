#pragma strict


function OnTriggerEnter(other: Collider)
{
	
	if(other.gameObject.tag == "Player"){
		other.gameObject.SendMessage("ApplyDamage", 10);
	
		//Destroy(gameObject);

	}
	
}

function Start () {

}

function Update () {

}