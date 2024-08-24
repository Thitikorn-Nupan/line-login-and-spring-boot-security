package com.ttknpdev.understandlineloginintegratewithwebapp.control;

import com.ttknpdev.understandlineloginintegratewithwebapp.log.Logback;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/api")
public class LineLoginTemplateControl {

    private Logback logback;

    public LineLoginTemplateControl() {
        logback = new Logback(LineLoginTemplateControl.class);
    }

    @GetMapping("/app")
    public String index(Model model,
                        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User) {
        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());

        logback.log.warn("*** OAuth2User ****\n" +
                        "oauth2User.getAttributes() : {}\n" +
                        "oauth2User.getName() : {}\n" +
                        "oauth2User.getAuthorities() : {}\n" +
                        "***********************"
                , oauth2User.getAttributes(),
                oauth2User.getName(),
                oauth2User.getAuthorities()
        );

        logback.log.warn("*** OAuth2AuthorizedClient ****\n" +
                        "authorizedClient.getAccessToken().getTokenType().getValue() : {}\n" +
                        "authorizedClient.getAccessToken().getScopes() : {}\n" +
                        "authorizedClient.getAccessToken().getTokenValue() : {}\n" +
                        "authorizedClient.getClientRegistration().getClientName() : {}\n" +
                        "***********************"
                , authorizedClient.getAccessToken().getTokenType().getValue(),
                authorizedClient.getAccessToken().getScopes(),
                authorizedClient.getAccessToken().getTokenValue(),
                authorizedClient.getClientRegistration().getClientName()
        );

        return "index";
    }

    @GetMapping("/app/test")
    public ResponseEntity index2(
            @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
            @AuthenticationPrincipal OAuth2User oauth2User) {
        Map<String, Object> map = new HashMap<>();
        map.put("authorizedClient", authorizedClient);
        map.put("oauth2User", oauth2User);
        return ResponseEntity.ok(map);

    }
}
