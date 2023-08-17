package com.dope.wmsapp.common;

import com.dope.wmsapp.inbound.feature.api.ConfirmInboundApi;
import com.dope.wmsapp.inbound.feature.api.RegisterInboundApi;
import com.dope.wmsapp.inbound.feature.api.RegisterLPNApi;
import com.dope.wmsapp.inbound.feature.api.RejectInboundApi;
import com.dope.wmsapp.location.feature.api.AssignLocationLPNApi;
import com.dope.wmsapp.location.feature.api.RegisterLocationApi;
import com.dope.wmsapp.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }

    public static RegisterLocationApi registerLocation() {
        return new RegisterLocationApi();
    }

    public static AssignLocationLPNApi assignLocationLPN() {
        return new AssignLocationLPNApi();
    }

    public RegisterInboundApi registerInbound() {
        return new RegisterInboundApi();
    }

    public ConfirmInboundApi confirmInbound() {
        return new ConfirmInboundApi();
    }

    public RejectInboundApi rejectInbound() {
        return new RejectInboundApi();
    }

    public RegisterLPNApi registerLPN() {
        return new RegisterLPNApi();
    }
}
