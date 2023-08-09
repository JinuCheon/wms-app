package com.dope.wmsapp.inbound.feature;

import com.dope.wmsapp.inbound.domain.Inbound;
import com.dope.wmsapp.inbound.domain.InboundRepository;

class RejectInbound {
    private final InboundRepository inboundRepository;

    public RejectInbound(final InboundRepository inboundRepository) {
        this.inboundRepository = inboundRepository;
    }

    public void request(final Long inboundNo, final Request rejectionReason) {
        final Inbound inbound = inboundRepository.getBy(inboundNo);
        inbound.reject(rejectionReason.rejectionReason);
    }

    public static class Request {
        private final String rejectionReason;

        public Request(final String rejectionReason) {
            this.rejectionReason = rejectionReason;
        }
    }
}
