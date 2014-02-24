package com.cs190.project.UserConfiguration;

import java.io.BufferedReader;
import java.io.StringReader;

import com.google.gson.Gson;

public class JsonManip {

	public static UserConfigs jsonToUserConfigs() {

		UserConfigs uc = null;

		/* This will eventually go away and the json will be fetched online */
		BufferedReader br = new BufferedReader(
				new StringReader(
						"{\"name\":\"Willys Setup\",\"plantName\":\"Tomatoes\",\"currentStage\":1,\"active\":true,\"totalStages\":3,\"_id\":\"53090056652420b20743da0b\",\"date\":\"2014-02-22T19:53:58.189Z\",\"stages\":[{\"dateTimeStart\":\"2014-02-22T19:53:58.183Z\",\"dateTimeEnd\":\"2014-02-22T19:53:58.183Z\",\"_id\":\"53090056652420b20743da0c\",\"wireless\":[{\"name\":\"Fan\",\"wirelessID\":\"someID\",\"_id\":\"53090056652420b20743da0d\",\"data\":[{\"power\":40,\"distance\":23,\"_id\":\"53090056652420b20743da0e\"}],\"timer\":{\"timeOn\":\"2014-02-22T19:53:58.183Z\",\"duration\":2.5}}],\"sensors\":{\"tempSensor\":{\"min\":70,\"max\":80,\"name\":\"Water Temperature\",\"data\":[{\"date\":\"2014-02-22T19:53:58.183Z\",\"value\":78,\"_id\":\"53090056652420b20743da10\"},{\"date\":\"2014-02-22T19:53:58.183Z\",\"value\":79,\"_id\":\"53090056652420b20743da0f\"}]},\"phSensor\":{\"min\":4,\"max\":7,\"name\":\"pH\",\"data\":[{\"date\":\"2014-02-22T19:53:58.183Z\",\"value\":5,\"_id\":\"53090056652420b20743da12\"},{\"date\":\"2014-02-22T19:53:58.183Z\",\"value\":3,\"_id\":\"53090056652420b20743da11\"}]}}}]}"));

		Gson gson = new Gson();
		uc = gson.fromJson(br, UserConfigs.class);

		return uc;

	}

}
