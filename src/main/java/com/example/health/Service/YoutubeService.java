package com.example.health.Service;
import com.example.health.Dto.YoutubeDto;
import com.example.health.entity.Video;
import com.example.health.repository.VideoRepository;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class YoutubeService {
    @Autowired
    VideoRepository videoRepository;
    private static String PROPERTIES_FILENAME = "youtube.properties";

    /** Global instance of the HTTP transport. */
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();

    /** Global instance of the max number of videos we want returned (50 = upper limit per page). */
    private static final long NUMBER_OF_VIDEOS_RETURNED = 15;

    /** Global instance of Youtube object to make all API requests. */
    private static YouTube youtube;

    public YoutubeDto[] getshoulder(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자어깨운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getidu(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자이두운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getsamdu(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자삼두운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getchest(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자가슴운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getback(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자등운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getleg(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자하체운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();


            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getbock(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자복근운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getrunning(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자유산소운동루틴";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }
    public YoutubeDto[] getstretch(){
        YoutubeDto[] arr=new YoutubeDto[15];
        Properties properties = new Properties();
        try {
            InputStream in = YoutubeService.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }catch (NullPointerException e){

        }

        try {
            /*
             * The YouTube object is used to make all API requests. The last argument is required, but
             * because we don't need anything initialized when the HttpRequest is initialized, we override
             * the interface and provide a no-op function.
             */
            youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {}
            }).setApplicationName("youtube-cmdline-search-sample").build();

            // Get query term from user.
            String queryTerm = "남자운동전스트레칭";

            YouTube.Search.List search = youtube.search().list("id,snippet");
            /*
             * It is important to set your API key from the Google Developer Console for
             * non-authenticated requests (found under the Credentials tab at this link:
             * console.developers.google.com/). This is good practice and increased your quota.
             */
            search.setKey("AIzaSyAdHkZrfOgwpUoBQZkBN_N3SsOZFm2nKd8");
            search.setQ(queryTerm);
            /*
             * We are only searching for videos (not playlists or channels). If we were searching for
             * more, we would add them as a string like this: "video,playlist,channel".
             */
            search.setType("video");
            /*
             * This method reduces the info returned to only the fields we need and makes calls more
             * efficient.
             */
            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
            SearchListResponse searchResponse = search.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();
            Iterator<SearchResult> iterator = searchResultList.iterator();
            int i=0;
            while (iterator.hasNext()) {

                SearchResult singleVideo = iterator.next();
                ResourceId rId = singleVideo.getId();
                videoRepository.save(new Video(rId.getVideoId()));

                // Double checks the kind is video.
                if (rId.getKind().equals("youtube#video")) {
                    Thumbnail thumbnail =(Thumbnail) singleVideo.getSnippet().getThumbnails().get("default");
                    arr[i]=new YoutubeDto(singleVideo.getSnippet().getTitle(),thumbnail.getUrl(),rId.getVideoId());
                }
                i++;
            }
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return arr;
    }



}
