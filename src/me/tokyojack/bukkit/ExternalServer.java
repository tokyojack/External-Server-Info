package com.valeon.core.serverCount;

import com.github.kevinsawicki.http.HttpRequest;
import com.google.gson.Gson;
import lombok.Getter;

@Getter
public class ExternalServer {

    private boolean status;
    private String ip;
    private int port;
    private int ping;
    private String version;
    private String protocol;
    private Players players;
    private Motds motds;

    public ExternalServer(String ip){
        Gson gson = new Gson();
        String response = HttpRequest.get("https://use.gameapis.net/mc/query/info/" + ip).body();
        ExternalServer es = gson.fromJson(response, ExternalServer.class);

        this.status = es.isStatus();
        this.ip = ip;
        this.port = es.getPort();
        this.ping = es.getPing();
        this.version = es.getVersion();
        this.protocol = es.getProtocol();
        this.players = es.getPlayers();
        this.motds = es.getMotds();
    }

}

