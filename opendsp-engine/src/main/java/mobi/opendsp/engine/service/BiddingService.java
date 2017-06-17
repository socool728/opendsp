package mobi.opendsp.engine.service;

import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;

import mobi.opendsp.proto.OpenDsp.BiddingReq;
import mobi.opendsp.proto.OpenDsp.BiddingRsp;
import mobi.opendsp.proto.OpenDsp.BiddingService.BlockingInterface;
import mobi.opendsp.proto.OpenDsp.BiddingService.Interface;

/**
 * @author wangweiping
 *
 */
public class BiddingService implements BlockingInterface, Interface {

	@Override
	public void bidding(RpcController controller, BiddingReq request, RpcCallback<BiddingRsp> done) {
		try {
			done.run(bidding(controller, request));
		} catch (ServiceException ex) {
		}
	}

	@Override
	public BiddingRsp bidding(RpcController controller, BiddingReq request) throws ServiceException {
		return null;
	}
}
