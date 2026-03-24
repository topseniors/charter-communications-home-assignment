export interface CustomerRewards {
  customerId: string;
  monthlyPoints: Record<string, number>;
  totalPoints: number;
}
