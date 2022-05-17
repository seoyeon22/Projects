#pragma strict

function OnTriggerEnter(other: Collider)
{
	if(other.gameObject.tag == "Player")
	{
		Application.LoadLevel("EndingScene");
	}	
}


function Start () {

}

function Update () {

}