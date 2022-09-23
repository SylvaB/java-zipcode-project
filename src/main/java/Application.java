import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kenzie.app.data.zipcode.Place;
import com.kenzie.app.data.zipcode.ZipCodeDTO;
import com.kenzie.app.format.AddressFormatUtil;
import com.kenzie.app.http.HttpUtil;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        try {
       //declare variables
        String BASE_URL = "https://api.zippopotam.us/us/";
        Scanner scanner = new Scanner(System.in);
        String recipientName;
        String streetAddress;
        String city;
        String state;
        String zipCode = "";

       //read in user input - scanner
        System.out.println("Enter recipient name: ");
        recipientName = scanner.nextLine();
        System.out.println("Enter street address: ");
        streetAddress = scanner.nextLine();
        System.out.println("Enter city: ");
        city = scanner.nextLine();
        System.out.println("Enter state: ");
        state = scanner.nextLine();

        //replace spaces in city - Los Angeles
        String tempCity = city.replace(" ","%20");

        //format URL with user city and state
        String finalURL = BASE_URL+state + "/"+ tempCity;
        //call Get
        String httpResponse = HttpUtil.makeGETRequest(finalURL);


        //if return string contains 404, don't return object map
        if(httpResponse.contains("GET request failed")){
            System.out.println("No zip code found");
        }
        else{
            //ObjectMapper to retrieve zip code
            //1. Instantiate ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            //2. DeclareDTO
            ZipCodeDTO zipCodeDTO;
            //3. Set DTO to objectMapper.readValue()
            zipCodeDTO = objectMapper.readValue(httpResponse, ZipCodeDTO.class);
           // zipCodeDTO.getPlaces().get(0);

            List<Place> places =zipCodeDTO.getPlaces();
            //if 1 zipCode returned, set zipCode
            if(zipCodeDTO.getPlaces().size() ==1){
                zipCode = zipCodeDTO.getPlaces().get(0).getPostCode();
            }
            else{
                //loop and display all zipcodes
                for(int i = 0; i< zipCodeDTO.getPlaces().size(); i++){
                    System.out.println(i + ") " + zipCodeDTO.getPlaces().get(i).getPostCode());
                }
            }
            //prompt user to select zipcode
            System.out.println("Choose a zipcode");
            int choice = scanner.nextInt();
            scanner.nextLine();

            //set zipCode based on selection
            zipCode = zipCodeDTO.getPlaces().get(choice).getPostCode();
        }
        

        //print out final address
            System.out.println(recipientName);
            System.out.println(AddressFormatUtil.formatStreetAddress(streetAddress));
            System.out.println(AddressFormatUtil.formatAddress(city + ", " + state + " " + zipCode));

        } catch (Exception e) {
            System.out.println("Unexpected exception: "+ e);
        }
    }
//    public static void main_backup(String[] args) {
//
//        try{
//            String URL = "https://api.zippopotam.us/us/mo/saint%20louis";
//            String httpResponse;
//            httpResponse = HttpUtil.makeGETRequest(URL);
//            System.out.println(httpResponse);
//
//            //JSON Object mapping
//            //1. instantiate ObjectMapper
//            //2. create DTO (in order to map data into)
//            //3. readValue()
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            ZipCodeDTO zipObj;
//            //zipObj = objectMapper.readValue(<input>, <pointer or reference);
//            zipObj = objectMapper.readValue(httpResponse,ZipCodeDTO.class);
//
//            //print out State, City(place name), Zipcode(post code)
//
//            //if there is only one place and one zipcode
//            System.out.println("State: "+ zipObj.getState()+"\n"+ "City: " + zipObj.getPlaces().get(0).getPlace_name()
//                    +"\n"+ "Zipcode: " + zipObj.getPlaces().get(0).getPostCode());
//
//            //if(<there is only one item, set zipCode to that>){
//            if(zipObj.getPlaces().size() ==1){
//                System.out.println("Only one zip code: " + zipObj.getPlaces().get(0).getPostCode());
//            }
//            //}else if(<list size is greater than 1>){
//            else if(zipObj.getPlaces().size()>1){
//                for(int i = 0;i< zipObj.getPlaces().size();i++){
//                System.out.println("State: "+ zipObj.getState()+"\n"+ "City: " + zipObj.getPlaces().get(i).getPlace_name()
//                        +"\n"+ "Zipcode: " + zipObj.getPlaces().get(i).getPostCode());
//            }
//            }
//            zipObj.getPlaces(); // here is my list
//            System.out.println("List! "+zipObj.getPlaces());
//
//    //List<Places> placesList =
//        }
//        catch (Exception e){
//            System.out.println("Unexpected exception: " + e);
//        }
//    }

}
