package com.charter.api.controllers;




public class RewardsController {

@Autowired
private RewardsProgramService rewardsProgramService;


@GetMapping(value = "v1/rewards")
@ApiResponses ({ @ApiResponse(responseCode = "200", description = "Ok"),
                 @ApiResponse(responseCode = "400", description = "Bad request", content = @Content (schema = @Schema (implementation = ErrorMessage.class))),}
)
public ResponseEntity<Rewards> getRewards(
    @RequestParam String userId,
    @RequestParam String dateStr
){
    TransactionInfo info = rewardsProgramService.packageParameters(userId, dateStr);
    rewardsProgramService.validateTransactionInfo(info);

    Rewards rewards = rewardsProgramService.calculateRewards(info);

    return ResponseEntity.ok(rewards);
}

}