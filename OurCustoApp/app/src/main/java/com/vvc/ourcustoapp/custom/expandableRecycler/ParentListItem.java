package com.vvc.ourcustoapp.custom.expandableRecycler;

import java.util.List;

public interface ParentListItem {

    List<?> getChildItemList();
    boolean isInitiallyExpanded();
}
