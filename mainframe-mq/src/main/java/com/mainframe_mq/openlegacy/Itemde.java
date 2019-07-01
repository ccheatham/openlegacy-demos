package com.mainframe_mq.openlegacy;


import lombok.Getter;
import lombok.Setter;
import org.openlegacy.core.annotations.rpc.*;
import org.openlegacy.core.definitions.RpcActionDefinition;
import org.openlegacy.core.rpc.actions.RpcActions.EXECUTE;
import org.openlegacy.providers.mq.MqBeanNames;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@RpcEntity(name = "Itemde", language = Languages.COBOL)
@RpcActions(actions = {
        @Action(action = EXECUTE.class, displayName = "Execute", alias = "execute", path = "ITEMDET ",
                actionProperties = {
                        @ActionProperty(name = MqBeanNames.SEND_QUEUE, value = "DEV.INPUT.QUEUE"),
                        @ActionProperty(name = MqBeanNames.RECEIVE_QUEUE, value = "DEV.OUT.QUEUE"),
                        @ActionProperty(name = MqBeanNames.SEND_QUEUE_MANAGER, value = "CSQ7"),
                        @ActionProperty(name = MqBeanNames.RECEIVE_QUEUE_MANAGER, value = "CSQ7"),
                        @ActionProperty(name = MqBeanNames.SEND_CHANNEL, value = "CSQ7.SVRCONN"),
                        @ActionProperty(name = MqBeanNames.RECEIVE_CHANNEL, value = "CSQ7.SVRCONN")
        })})
public class Itemde implements org.openlegacy.core.rpc.RpcEntity {

    @RpcNumericField(minimumValue = -99999999, maximumValue = 99999999)
    @RpcField(length = 4, originalName = "ITEM-NUM", legacyType = "Binary Integer", direction = Direction.INPUT)
    private Integer itemNum;

    @RpcField(direction = Direction.OUTPUT)
    private Item item;

    @Override
    public List<RpcActionDefinition> getActions() {
        return new ArrayList<>();
    }

	@Getter
	@Setter
	@RpcPart(name = "Item", originalName = "ITEM", displayName = "Item")
	public static class Item {
		@RpcField()
		private ItemRecord newName;
		@RpcField()
		private Shipping shipping;
	}

	@Getter
	@Setter
	@RpcPart(name = "ItemRecord", originalName = "ITEM-RECORD", displayName = "ITEMRECORD")
	public static class ItemRecord {
		@RpcField(length = 16, originalName = "ITEM-NAME", legacyType = "Char")
		private String itemName;
		@RpcField(length = 28, originalName = "DESCRIPTION", legacyType = "Char")
		private String description;
		@RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
		@RpcField(length = 2, originalName = "WEIGHT", legacyType = "Binary Integer")
		private Integer weight;
	}

	@Getter
	@Setter
	@RpcPart(name = "Shipping", originalName = "SHIPPING", displayName = "Shipping")
	public static class Shipping {
		@RpcField(length = 10, originalName = "SHIPPING-METHOD", legacyType = "Char")
		private String shippingMethod;
		@RpcNumericField(minimumValue = -9999, maximumValue = 9999, decimalPlaces = 0)
		@RpcField(length = 2, originalName = "DAYS", legacyType = "Binary Integer")
		private Integer days;
	}
}