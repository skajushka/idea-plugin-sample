package io.eroshenkoam.idea.jira;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author eroshenkoam (Artem Eroshenko).
 */
public interface JiraClient {

    @GET("rest/api/2/myself")
    JiraUser getUser();

    @GET("rest/api/2/search")
    JiraFilter findIssue(@Query(value = "jql", encoded = true) String jql);

    @GET("rest/api/2/issue/{key}")
    JiraIssue getIssue(@Path("key") String key);

    @POST("rest/api/2/issue")
    JiraIssue createIssue(@Body JiraIssue issue);
}
