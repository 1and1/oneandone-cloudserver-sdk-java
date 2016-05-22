/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneandone.sdk;

import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Response.Image;
import com.oneandone.rest.POJO.Requests.UpdateImageRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ImageApi extends OneAndOneAPIBase {

    public ImageApi() {
        super("images", null);
    }

   /**
     * Returns a list of your images.
     * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My server
     * @param fields Returns only the parameters requested: fields=id,name,description,hardware.ram
     * @return List Image
     * @throws RestClientException
     * @throws IOException
     */
    public List<Image> getAllImages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
        String queryUrl = getUrlBase().concat(resource).concat("?");
        boolean firstParameter = true;

        if (page != 0) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("page=").concat(Integer.toString(page));
            firstParameter = false;
        }
        if (perPage != 0) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("per_page=").concat(Integer.toString(perPage));
            firstParameter = false;

        }
        if (sort != null && !sort.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("sort=").concat(sort);
            firstParameter = false;
        }
        if (query != null && !query.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("q=").concat(query);
            firstParameter = false;
        }
        if (fields != null && !fields.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("fields=").concat(fields);
        }
        Image[] result = client.get(queryUrl, null, Image[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns information about one flavor.
     * @param imageId Unique server's identifier.
     * @return Image 
     * @throws RestClientException
     * @throws IOException
     */
    public Image getImage(String imageId) throws RestClientException, IOException{
        return client.get(getUrlBase().concat(resource).concat("/").concat(imageId), null, Image.class);
    }

    /**
     * Adds a new image.
     * @param object Image
     * @return Image  
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public Image createImage(Image object) throws RestClientException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return client.create(getUrlBase().concat(resource), object, Image.class, 202);
    }

    /**
     * Removes an image.
     * @param imageId
     * @return Image
     * @throws RestClientException
     * @throws IOException
     */
    public Image deleteImage(String imageId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(imageId), Image.class);
    }

    /**
     * Modifies an image.
     * @param imageId Unique image's identifier.
     * @param object UpdateImageRequest
     * @return Image
     * @throws RestClientException
     * @throws IOException
     */
    public Image updateImage(String imageId, UpdateImageRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(imageId), object, Image.class, 200);
    }
}
