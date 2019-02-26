package com.lhc.dto;

import com.lhc.business.dto.RankingCell;
import java.util.List;
import lombok.Data;

@Data
public class Rankings {

    List<RankingCell> top;

    List<RankingCell> flop;

    public Rankings(List<RankingCell> top, List<RankingCell> flop) {
        this.top = top;
        this.flop = flop;
    }
}
