import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class MethodRest {
    static String baseUri="https://jsonplaceholder.typicode.com/posts";

    static HashMap<String, String> APIHeader = new HashMap<String, String>();

    public void putRest(){                          //PUT
        String putStr="{\n" +
                "\t  \"title\" : \"foo\",\n" +
                "      \"body\" : \"bar\",\n" +
                "      \"userId\" : 1\n" +
                "}";
        Response response = given().headers(APIHeader).body(putStr).
                when().post(baseUri).
                then().assertThat().statusCode(201).extract().response();

        JsonPath path2 = new JsonPath(response.asString());
        System.out.println(response.asString());
        assert path2.get("title") == "foo";
        assert path2.get("body") == "bar";
        assert path2.get("userId") == "1";
    }


    public void postRest(){                         //POST
        String postStr="{\n" +
                "\t  \"title\" : \"foo\",\n" +
                "      \"body\" : \"bar\",\n" +
                "      \"userId\" : 1\n" +
                "}";
        Response response = given().headers(APIHeader).body(postStr).
                when().post(baseUri).
                then().assertThat().statusCode(201).extract().response();

        JsonPath path =  new JsonPath(response.asString());
        System.out.println(response.asString());
        assert path.get("title") == "foo";
        assert path.get("body") == "bar";
        assert path.get("userId") == "1";
    }


    public void deleteRest(){     //DELETE
        APIHeader.put("Content-type","application/json");
        APIHeader.put("charset","UTF-8");
        Response response = given().headers(APIHeader).
                when().delete(baseUri+"/1").
                then().assertThat().statusCode(200).extract().response();
        System.out.println(response);
    }


    public void getRest(){              //GET   
        Response response = given().headers(APIHeader).
                when().get(baseUri).
                then().assertThat().statusCode(200).extract().response();

        JsonPath path1 = new JsonPath(response.asString());
        System.out.println(response.asString());
    }


    public static void main(String[] args) {
        MethodRest.APIHeader.put("Content-type", "application/json");
        MethodRest.APIHeader.put("charset", "UTF-8");

        MethodRest rest =new MethodRest();
        rest.getRest();
        rest.postRest();
        rest.putRest();
        rest.deleteRest();
    }
}
