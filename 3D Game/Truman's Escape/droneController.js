#pragma strict

var fixedPoint : Vector3;
var angularSpeed : float;
var radius : float;
var x : float;
var z : float;
var w : float;


function OnTriggerEnter(other: Collider)
{
	other.gameObject.SendMessage("ApplyDamage", 30);
	Debug.Log("Drone_ontriggerenter");
}


function OnCollisionEnter(col: Collision)
{
	if(col.gameObject.CompareTag("Player"))
	{
		Debug.Log("Drone-Player-Collision");
	}
}

function Start () 
{
	fixedPoint = transform.position;
	

}

function Update () 
{
	w += angularSpeed * Time.deltaTime;
 
    x = Mathf.Cos(w) * radius;
    z = Mathf.Sin(w) * radius;
 
    transform.position = new Vector3(fixedPoint.x + x, fixedPoint.y, fixedPoint.z + z);
    transform.Rotate(Vector3.up * angularSpeed);


}