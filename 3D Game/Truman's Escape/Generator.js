#pragma strict

var intervalMin : float = 0.5;
var intervalMax : float = 1.5;

var cubePrefab : GameObject;
//var spawnSE : AudioClip;

function Start () 
{
	while(true)
	{
		var player : GameObject = GameObject.FindWithTag("Player");
	
		if(player != null)
		{
			var position : Vector3 = player.transform.position;
			
		}
		
		yield WaitForSeconds(Random.Range(intervalMin, intervalMax));
		
		//var theta : float = Random.Range(0.0, Mathf.PI * 3.0);
		//position = Vector3(position.x + Mathf.Cos(theta), 0.0, position.z + Mathf.Sin(theta));
		position = Vector3(position.x - Random.Range(5, 30), 0.0, position.z + Random.Range(-10, 10));
		position.y = 4;

		
		Instantiate(cubePrefab, position, Quaternion.identity);
		//audio.PlayOneShot(spawnSE);
	}
}

function Update () {

}