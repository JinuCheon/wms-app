package com.dope.wmsapp.common;

import com.dope.wmsapp.inbound.feature.api.ConfirmInboundApi;
import com.dope.wmsapp.inbound.feature.api.RegisterInboundApi;
import com.dope.wmsapp.inbound.feature.api.RegisterLPNApi;
import com.dope.wmsapp.inbound.feature.api.RejectInboundApi;
import com.dope.wmsapp.location.feature.api.AssignInventoryApi;
import com.dope.wmsapp.location.feature.api.RegisterLocationApi;
import com.dope.wmsapp.outbound.feature.api.RegisterPackageMaterialApi;
import com.dope.wmsapp.product.feature.api.RegisterProductApi;

public class Scenario {

    public static RegisterProductApi registerProduct() {
        return new RegisterProductApi();
    }

    public static RegisterLocationApi registerLocation() {
        return new RegisterLocationApi();
    }

    public static AssignInventoryApi assignInventory() {
        return new AssignInventoryApi();
    }

    public static RegisterPackageMaterialApi registerPackageMaterial() {
        return new RegisterPackageMaterialApi();
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
