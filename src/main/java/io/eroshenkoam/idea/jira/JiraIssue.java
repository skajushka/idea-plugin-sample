package io.eroshenkoam.idea.jira;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author eroshenkoam (Artem Eroshenko).
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JiraIssue implements Serializable {

    private static final long serialVersionUID = 1L;

    private String key;
    private JiraIssueFields fields;

}
