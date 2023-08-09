package com.dope.wmsapp.common;

import com.dope.wmsapp.inbound.feature.api.ConfirmInboundApi;
import com.dope.wmsapp.inbound.feature.api.RegisterInboundApi;
import com.dope.wmsapp.inbound.feature.api.RejectInboundApi;
import com.dope.wmsapp.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
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
}
